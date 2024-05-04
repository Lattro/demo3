package com.example.demo.helpers;

import com.example.demo.models.UserModel;
import com.example.demo.repos.UserRepo;
import org.springframework.stereotype.Component;

@Component
public class UserHelper
{
    final
    UserRepo userRepo;

    public UserHelper(UserRepo userRepo) {
        this.userRepo = userRepo;
        userHelper = this;
    }
    private static UserHelper userHelper  = null;


    public static void saveUser(UserModel userModel)
    {
        userHelper.userRepo.save(userModel);
    }
    public static UserModel findUser(String tgId)
    {
        UserModel userModel;
        userModel = userHelper.userRepo.findUserModelByTgId(tgId);
        return  userModel;
    }
}