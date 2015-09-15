package com.omazan.EJBs.Interfaces;

import javax.ejb.Remote;

import com.omazan.Misc.DatabaseState;

@Remote
public interface DBSnapshotEJBRemote {

	public DatabaseState SynchronizeDBwithMobileClients() ;
}
