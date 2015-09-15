package com.omazan.EJBs.Interfaces;

import javax.ejb.Local;

import com.omazan.Misc.DatabaseState;

@Local
public interface DBSnapshotEJBLocal {

	public DatabaseState SynchronizeDBwithMobileClients() ;
}
