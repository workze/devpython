package com.ze.devhelper.service;

import com.ze.devhelper.dto.Host;
import com.ze.devhelper.dao.HostDao;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

import java.util.ArrayList;
import java.util.List;

public class HostService {

    private HostDao hostDao = null;

    public HostService() {

//        final DataSourceFactory dataSourceFactory = new DataSourceFactory();
//        dataSourceFactory.setDriverClass("org.sqlite.JDBC");
//        dataSourceFactory.setUrl("jdbc:sqlite:F:/oschina/intellijU/java/resource/devhelper.sqlite");
//        dataSourceFactory.setInitialSize(1);
//        final DBI dbi = new DBIFactory().build(null, dataSourceFactory, "test");

    }

    public HostService(HostDao hostDao) {
        this.hostDao = hostDao;
    }

    public boolean initialize(){
        return true;
    }

    public List<Host> getAllHosts(){
        List<Host> hosts = new ArrayList<Host>();
        Host host = new Host().mock();
        hosts.add(host);
        return hosts;
    }
}
