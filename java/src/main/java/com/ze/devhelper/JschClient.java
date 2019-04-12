package com.ze.devhelper;

import com.jcraft.jsch.*;
import com.ze.devhelper.dto.Host;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class JschClient {

    private JSch jsch = null;
    private Session session = null;
    private Host host = null;
    private String alias = "Host";
    private boolean rootMode = true;

    public JschClient() {
    }

    public JschClient(Host host) {
        this.host = host;
        Properties config = new Properties();
        config.setProperty("user", host.getUser());
        config.setProperty("ip", host.getIp());
        config.setProperty("port", String.valueOf(host.getPort()));
        config.setProperty("password", host.getPassword());
        initSession(config);
    }

    public JschClient(Properties config) {
        initSession(config);
    }

    private void initSession(Properties config) {
        jsch = new JSch();
        if (!"root".equals(config.getProperty("user"))) {
            rootMode = false;
        }
        try {
            session = jsch.getSession(config.getProperty("user", "root"),
                    config.getProperty("ip", "127.0.0.1"),
                    Integer.valueOf(config.getProperty("port", "22")));

            session.setPassword(config.getProperty("password", ""));
            session.setUserInfo(new MyUserInfo());
            // 跳过鉴权的确认
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(GlobalConfig.SESSION_TIMEOUT);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public Result execPythonScript(String scriptContent) {
        if (session == null) {
            return new Result(1, "session null");
        }

        Result uploadResult = uploadString(scriptContent);
        if (uploadResult.status != 0) {
            return uploadResult;
        }
        String fileName = uploadResult.message;
        Result result =  execShellCommand("sh " + fileName);

        return result;
    }

    public Result execShellScript(String scriptContent) {
        if (session == null) {
            return new Result(1, "session null");
        }

        Result uploadResult = uploadString(scriptContent);
        if (uploadResult.status != 0) {
            return uploadResult;
        }
        String fileName = uploadResult.message;
        Result result =  execShellCommand("sh " + fileName);

        return result;
    }

    public Result execShellCommand(String cmd, boolean sudoMode) {
        if (sudoMode) {
            cmd += "sudo ";
        }
        return execShellCommand(cmd);
    }

    public Result execShellCommand(String cmd) {
        if (session == null) {
            return new Result(1,"null session");
        }
        ChannelExec channelExec = null;
        StringBuffer buf = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(cmd);
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            InputStream in = channelExec.getInputStream();
            channelExec.connect();

            int res = -1;
            buf = new StringBuffer(1024);
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    buf.append(new String(tmp, 0, i));
                }
                if (channelExec.isClosed()) {
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (channelExec != null) {
                channelExec.disconnect();
            }
        }
        return new Result(0,buf.toString());
    }

    public Result uploadFile(final String localfile, String remoteFile) {
        return new Result();
    }

    public Result uploadString(final String fileContent) {
        if (session == null) {
            return new Result(1, "session null.");
        }
        ChannelSftp channel = null;
        String fileName = "/var/uploadString";
        try {
            channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            OutputStream os = channel.put(fileName);
            byte[] all = fileContent.getBytes();
            os.write(all);
            return new Result(0, fileName);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }

        return new Result(1, "JSchException");
    }

    public Result downloadFile(String remoteFile,String localfile) {
        if (session == null) {
            return new Result(1, "session null.");
        }
        ChannelSftp channel = null;
        try {
            channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            channel.get(remoteFile,localfile);
            return new Result(0,localfile);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }

        return new Result(1, "JSchException");
    }

    public Result downloadString(String remoteFile) {
        if (session == null) {
            return new Result(1, "session null.");
        }
        ChannelSftp channel = null;
        try {
            channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            InputStream is = channel.get(remoteFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer(1024);
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp);
            }
            return new Result(0, sb.toString());
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }

        return new Result(1, "JSchException");
    }

    public Result execRemoteScript(String remotefile) {


        return new Result();
    }

    public Result execLocalScript(String localfile) {
        return new Result();
    }

    public void heartbeat() {

    }

    public boolean isHealth() {
        return true;
    }

    public boolean close() {
        if (session != null) {
            session.disconnect();
        }
        return true;
    }

    /*
     * 自定义UserInfo
     * */
    private static class MyUserInfo implements UserInfo {

        public String getPassphrase() {
            return null;
        }

        public String getPassword() {
            return null;
        }

        public boolean promptPassword(String s) {
            return false;
        }

        public boolean promptPassphrase(String s) {
            return false;
        }

        public boolean promptYesNo(String s) {
            return true;
        }

        public void showMessage(String s) {
        }
    }
}
