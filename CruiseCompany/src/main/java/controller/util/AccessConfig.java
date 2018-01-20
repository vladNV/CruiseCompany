package controller.util;

import model.util.Role;

import java.util.*;
import java.util.regex.Pattern;

public class AccessConfig {
    private static AccessConfig instances;

    private AccessConfig() {
        init();
    }

    public static AccessConfig getAccessConfig() {
        if (instances == null) {
            synchronized (AccessConfig.class) {
                if (instances == null) {
                    instances = new AccessConfig();
                }
            }
        }
        return instances;
    }

    private void init() {
        Set<Role> all = new HashSet<>(
                Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN)
        );
        Set<Role> authorized = new HashSet<>(
                Arrays.asList(Role.USER, Role.ADMIN)
        );
        Set<Role> unauthorized = new HashSet<>(
                Collections.singletonList(Role.GUEST)
        );
        Set<Role> admin = new HashSet<>(
                Collections.singletonList(Role.ADMIN)
        );
        access = new HashMap<>();
        access.put(Pattern.compile("/profile"), authorized);
        access.put(Pattern.compile("/login"), unauthorized);
        access.put(Pattern.compile("/main"), all);
        access.put(Pattern.compile("/"), all);
        access.put(Pattern.compile("/registration"), unauthorized);
        access.put(Pattern.compile("/logout"), authorized);
        access.put(Pattern.compile("/tour/\\d{1,5}"), all);
        access.put(Pattern.compile("/ticket/standard"), authorized);
        access.put(Pattern.compile("/ticket/premium"), authorized);
        access.put(Pattern.compile("/ticket/luxe"), authorized);
    }

    private HashMap<Pattern,  Set<Role>> access;
    public HashMap<Pattern,  Set<Role>> getAccess() {
        return access;
    }
}
