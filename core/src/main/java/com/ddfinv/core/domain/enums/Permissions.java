package com.ddfinv.core.domain.enums;

import java.util.EnumSet;
import java.util.Set;
/**
 * For now we will use an enum based default permissions system. 
 * If more features are added to the application, transition to database based.
 * It will allow more modularity and easier modification / addition of permissions.
 * 
 */
public enum Permissions {

    // template
    // permission(""),

    /**
     * UserAccount level:
     */
    VIEW_ACCOUNT("View the details of you UserAccount."),
    EDIT_MY_DETAILS("Edits the account details of this specific UserAccount instance."),
    CREATE_USER("Creates a new UserAccount."),

    /**
     * Admin level:
     */
    EDIT_USER("Edits the details of a specific UserAccount."),
    DELETE_USER("Removes a UserAccount from the system."),
    EDIT_EMPLOYEE("Edit the details of a specific Employee account instance."),
    CREATE_EMPLOYEE("Creates a new Employee account instance."),



    /**
     * Employee level:
     */
    CREATE_CLIENT("Creates a client account from by upgrading an existing Guest account."),
    EDIT_CLIENT("Edits the details of an existing Client account."),
    VIEW_CLIENT("Views the details of a specific Client account instance."),
    VIEW_CLIENTS("Lists the details of all Client account instances"),
    ASSIGN_CLIENT("Assigns an individual Client account instance to an Employeee partner."),
    CREATE_INVESTMENT("Creates a new investment for a specific Client account instance."),
    EDIT_INVESTMENT("Edits an existing investment for a specific Client account instance."),
    VIEW_EMPLOYEES("Lists the details of all Employee account instances."),
    VIEW_EMPLOYEE("Lists the details of a specific Employee account instance."),



    /**
     * Client level:
     */
    VIEW_INVESTMENT("Views the details of a specific investment for this Client account instance."),





    /**
     * Guest level:
     */
    REQUEST_CLIENT_ACCOUNT("Request upgrade to Client account status with the firm.");


    public String getDescription() {
        return description;
    }

    private final String description;

    Permissions(String description){
        this.description = description;
    }

    /**
     * 
     */
    @Override
    public String toString(){
        return name();
    }

    /**
     * 
     * @return
     */
    public static Set<Permissions> getAllPermissions(){
        return EnumSet.allOf(Permissions.class);
    }


}
