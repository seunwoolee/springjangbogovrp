//package com.example.jangbogo.rest;
//
//
//import javax.validation.Valid;
//
//import com.example.jangbogo.model.User;
//import com.example.jangbogo.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@RestController
//@CrossOrigin(exposedHeaders = "errors, content-type")
//@RequestMapping("api/users")
//public class UserRestController {
//
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<Collection<User>> getAllUsers(){
//        Collection<User> users = new ArrayList<User>();
//        users.addAll(this.userService.findAllUsers());
//        if (users.isEmpty()){
//            return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
//        }
//
////        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
//        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
//    }
////    @PreAuthorize( "hasRole(@roles.ADMIN)" )
////    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
////    public ResponseEntity<User> addOwner(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception {
////        BindingErrorsResponse errors = new BindingErrorsResponse();
////        HttpHeaders headers = new HttpHeaders();
////        if (bindingResult.hasErrors() || (user == null)) {
////            errors.addAllErrors(bindingResult);
////            headers.add("errors", errors.toJSON());
////            return new ResponseEntity<User>(user, headers, HttpStatus.BAD_REQUEST);
////        }
////
////        this.userService.saveUser(user);
////        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
////    }
//}
