package org.example;

public class Main {
    public static void main(String[] args) {

        OSBBMemberCrud osbbMemberCrud = new OSBBMemberCrud();
        osbbMemberCrud.fwMigration();
        osbbMemberCrud.getMembersWithAutoNotAllowed();
    }
}




