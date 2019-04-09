package com.ze.devhelper.dao;

import com.ze.devhelper.Constants;
import com.ze.devhelper.dto.Host;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface HostDao {
    @SqlUpdate("INSERT INTO " + Constants.T_HOST + " (id, ip, port, user, password, alias, createtime, status, type) " +
            "VALUES (:id, :ip, :port, :user, :password, :alias, :createtime, :status, :type)")
    long insertHost(@BindBean Host host);

    long updateHost(Host host);

    int deleteHost(long hostId);

}
