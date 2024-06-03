package loginapp;

/**
 * Definition of the types of users
 */
public enum loginOption {
    Admin, User;
    private loginOption(){}

    public String value(){
        return name();
    }

    public static loginOption fromValue(String v){
        return valueOf(v);
    }
}
