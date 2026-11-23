package com.akshay.stockflow.auth;
import jakarta.validation.Valid; import jakarta.validation.constraints.NotNull; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.List;
@RestController @RequestMapping("/api/admin/users") public class AdminUserController {
private final UserAccountRepository users; public AdminUserController(UserAccountRepository users){this.users=users;}
@GetMapping public List<UserView> all(){return users.findAll().stream().map(u->new UserView(u.getId(),u.getEmail(),u.getFullName(),u.getRole(),u.isEnabled(),u.getCreatedAt())).toList();}
@PatchMapping("/{id}/role") public UserView role(@PathVariable Long id,@Valid @RequestBody RoleInput input){UserAccount u=users.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));u.setRole(input.role());return view(users.save(u));}
@PatchMapping("/{id}/status") public UserView status(@PathVariable Long id,@Valid @RequestBody StatusInput input){UserAccount u=users.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));u.setEnabled(input.enabled());return view(users.save(u));}
private UserView view(UserAccount u){return new UserView(u.getId(),u.getEmail(),u.getFullName(),u.getRole(),u.isEnabled(),u.getCreatedAt());}
public record RoleInput(@NotNull Role role){} public record StatusInput(boolean enabled){} public record UserView(Long id,String email,String fullName,Role role,boolean enabled,java.time.Instant createdAt){}
}
