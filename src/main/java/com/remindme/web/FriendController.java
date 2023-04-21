package com.remindme.web;

import com.remindme.entity.Friend;
import com.remindme.entity.UserAccount;
import com.remindme.service.FriendService;
import com.remindme.service.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/remindme/account/friend")
public class FriendController {
private final UserAccountService userAccountService;
private final FriendService friendService;

    @PostMapping(path = "add")
    @PreAuthorize(value = "authentication.principal.equals(#username)")
    public void addNewFriend(
        @NotNull @RequestParam(name = "username") String username,
        @NotNull @Valid @RequestBody Friend friend
        ) {
    UserAccount userAccount=userAccountService.getUserAccountByUserName(username);
    friendService.addNewFriend(userAccount,friend);
}
}
