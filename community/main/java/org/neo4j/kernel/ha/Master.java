package org.neo4j.kernel.ha;

import org.neo4j.kernel.IdType;

/**
 * Represents the master-side of the HA communication between master and slave.
 * A master will receive calls to these methods from slaves when they do stuff.
 */
public interface Master
{
    IdAllocation allocateIds( IdType idType );

    Response<Integer> createRelationshipType( SlaveContext context, String name );

    Response<LockResult> acquireNodeWriteLock( SlaveContext context, long... nodes );

    Response<LockResult> acquireNodeReadLock( SlaveContext context, long... nodes );

    Response<LockResult> acquireRelationshipWriteLock( SlaveContext context, long... relationships );

    Response<LockResult> acquireRelationshipReadLock( SlaveContext context, long... relationships );

    Response<Long> commitSingleResourceTransaction( SlaveContext context,
            String resource, TransactionStream transactionStream );
    
    Response<Void> doneCommitting( SlaveContext context );

    Response<Void> rollbackTransaction( SlaveContext context );
    
    Response<Void> pullUpdates( SlaveContext context );
}
