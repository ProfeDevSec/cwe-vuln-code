@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setEmail("user@example.com");
        user.setPhoneNumber("123-456-7890");
        return ResponseEntity.ok(user);
    }
}
