public class Admin_POJO
{
    private static final double PIN = 1920; // pin to verify the admin
    private String id ; // String variable to store the id of the admin
    private String pass; // String variable to store the pass of the admin

    // Getters and Setters

    static double getPIN()
    {
        return PIN;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {

        this.id = id;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }
}
