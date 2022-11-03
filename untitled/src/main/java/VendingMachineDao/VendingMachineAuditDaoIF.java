package VendingMachineDao;

public interface VendingMachineAuditDaoIF {


    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

    void writeAuditEntry();
}
