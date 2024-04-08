package ma.entraide.subvention.service;

import ma.entraide.subvention.entity.UserInfo;
import ma.entraide.subvention.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+username));
    }

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
    public List<UserInfo> getAllUser(){
         return userInfoRepository.findAll();
    }
    public UserInfo getUser(Integer id){
        return userInfoRepository.findById(id).get();
    }
    public UserInfo findUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOp = userInfoRepository.findByName(username);
        UserInfo userInfo = null;
        if(userInfoOp.isPresent()){
            userInfo = userInfoOp.get();
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
        return userInfo;
    }
    public String updateUser(Integer id, UserInfo updatedUserInfo) {
        try{
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(id);
        if (optionalUserInfo.isPresent()) {
            UserInfo existingUser = optionalUserInfo.get();
            existingUser.setName(updatedUserInfo.getName());
            existingUser.setEmail(updatedUserInfo.getEmail());
            existingUser.setRoles(updatedUserInfo.getRoles());
            existingUser.setPassword(passwordEncoder.encode(updatedUserInfo.getPassword())); // Update password

            // Save the updated user
            userInfoRepository.save(existingUser);
            return "User updated successfully";
        }else {
            return "User not found";
        }}catch (Exception e){
            return "Error occurred while updating user";
        }
    }

    public String deleteUser(Integer id) {
        UserInfo userInfo = userInfoRepository.findById(id).get();
        userInfoRepository.delete(userInfo);
        return "User deleted successfully";
    }
}
