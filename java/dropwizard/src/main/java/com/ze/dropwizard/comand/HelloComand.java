package com.ze.dropwizard.comand;

import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

public class HelloComand extends Command {

  public HelloComand(){
    super("cmd_name","cmd_desc");
  }

  @Override
  public void configure(Subparser subparser) {
    subparser.addArgument("-u")
            .dest("user")
            .type(String.class)
            .required(true);
  }

  @Override
  public void run(Bootstrap<?> bootstrap, Namespace namespace) throws Exception {
    System.out.println("HelloComand.run()");
  }
}
