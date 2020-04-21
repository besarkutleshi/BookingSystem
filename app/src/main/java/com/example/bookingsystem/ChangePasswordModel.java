package com.example.bookingsystem;

public class ChangePasswordModel {

    public String Email;
    public String OldPassword;
    public String NewPassword;
    public String ConfirmPassword;

    public ChangePasswordModel(String email, String oldPassword, String newPassword, String confirmPassword) {
        Email = email;
        OldPassword = oldPassword;
        NewPassword = newPassword;
        ConfirmPassword = confirmPassword;
    }
}
