package lewisdthall.fourprongedJSONfirefit;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase {
    private static FireBase instance;
    static FirebaseAuth auth;
    static FirebaseDatabase db;
    static DatabaseReference ref;

    private FireBase() {
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance("https://fourprongedentity-default-rtdb.asia-southeast1.firebasedatabase.app");
    }

    public static synchronized FireBase instance() {
        if (instance == null) {
            instance = new FireBase();
        }
        return instance;
    }

    public static void logout(){
        auth.signOut();
    }

    public static void ref(String path){
        ref = db.getReference(path);
    }
}
