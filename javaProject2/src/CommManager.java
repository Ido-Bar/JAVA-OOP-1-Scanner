public class CommManager {
    private Committee[] comms;
    private int commsSize;

    public CommManager(int newSize) {
        comms = new Committee[newSize];
        commsSize = 0;
    }

    public void addCommittee(String name, Lecturer chairman) {
        if (chairman.getDegreeRank().ordinal() < Lecturer.Degree.DR.ordinal()) {
            return;
        }
        Committee newComm = new Committee(name, chairman);

        boolean isOverSize = commsSize == comms.length;
        if (isOverSize) { doubleCommittees(); }

        comms[commsSize] = newComm;
        commsSize++;
    }

    public void removeCommittee(Committee comm) {
        for (int i = 0; i < commsSize; i++) {
            if (comms[i].equals(comm)) {
                for (int j = i; j < commsSize - 1; j++) {
                    comms[j] = comms[j + 1];
                }
                comms[commsSize - 1] = null;
                commsSize--;
                break;
            }
        }
    }

    public Committee[] getCommittees(){
        Committee[] activeCommitees = new Committee[commsSize];

        for (int i = 0; i < commsSize; i++) {
            activeCommitees[i] = comms[i];
        }

        return activeCommitees;
    }

    public Committee getCommitteeByName(String name){
        for (int i = 0; i < commsSize; i++) {
            if (comms[i].getName().equals(name)) {
                return comms[i];
            }
        }
        return null; 
    }

    private void doubleCommittees() {
        int elemsExtFactor = 2;
        int elemsSize = comms.length;

        Committee[] newElems = new Committee[elemsExtFactor * elemsSize];

        for (int i = 0; i < elemsSize; i++) {
            newElems[i] = comms[i];
        }
        comms = newElems;
    }
}
