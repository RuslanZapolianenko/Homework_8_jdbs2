package org.example;

public class Main {
    public static void main(String[] args) {

        OSBBMember osbbMember = new OSBBMember();
        osbbMember.fwMigration();
        System.out.println(osbbMember.getMembersWithAutoNotAllowed());
    }
}




