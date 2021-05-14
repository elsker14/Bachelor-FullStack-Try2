//package com.example.licentaBackendSB.auth;
//
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.licentaBackendSB.security.UserRole.*;
//
//@Repository("fakeUsersDB")  //in cazul in care exista mai multe imlementari, cu acest QUALIFIER va gasi unde sa se lege
//public class FakeApplicationUserDaoService implements ApplicationUserDao{
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
//        return getApplicationUsers()
//                .stream()
//                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
//                .findFirst();
//    }
//
//    private List<ApplicationUser> getApplicationUsers()
//    {
//        List<ApplicationUser> applicationUsers = Lists.newArrayList(
//                new ApplicationUser(
//                        ADMIN.getGrantedAuthorities(),
//                        "iancu",
//                        passwordEncoder.encode("1233"),
//                        true,
//                        true,
//                        true,
//                        true
//                        ),
//                new ApplicationUser(
//                        STUDENT.getGrantedAuthorities(),
//                        "checu",
//                        passwordEncoder.encode("1233"),
//                        true,
//                        true,
//                        true,
//                        true
//                ),
//                new ApplicationUser(
//                        ASSISTANT.getGrantedAuthorities(),
//                        "lixi",
//                        passwordEncoder.encode("1233"),
//                        true,
//                        true,
//                        true,
//                        true
//                )
//        );
//
//        return applicationUsers;
//    }
//}
