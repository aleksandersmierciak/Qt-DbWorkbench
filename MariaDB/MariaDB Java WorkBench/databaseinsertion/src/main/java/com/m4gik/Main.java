package com.m4gik;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.m4gik.common.Event;
import com.m4gik.common.EventMembership;
import com.m4gik.common.FriendRelation;
import com.m4gik.common.Group;
import com.m4gik.common.GroupMembership;
import com.m4gik.common.Location;
import com.m4gik.common.Post;
import com.m4gik.common.PostList;
import com.m4gik.common.Profile;
import com.m4gik.common.ReceiverPost;
import com.m4gik.common.School;
import com.m4gik.common.SchoolList;
import com.m4gik.common.UserStatus;
import com.m4gik.common.WorkPlace;
import com.m4gik.common.WorkPlaceList;
import com.m4gik.database.MariaDBConnection;

/**
 * The Main class, executing generated inserts for MariaDB database.
 * 
 * @author Michał Szczygieł <michal.szczygiel@wp.pl>
 * 
 */
public class Main {

    /**
     * Logger for event registration.
     */
    private final static Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * This method fills database with random insertion.
     * 
     * @param amount
     *            The amount of insertion.
     * @throws SQLException
     */
    private static void fillDatabase(Integer amount) throws SQLException {
        long start_time = System.nanoTime();

        UserStatus userStatus = new UserStatus(amount / 10);
        userStatus.insertRandomData();

        Location location = new Location(amount);
        location.insertRandomData();

        Profile profile = new Profile(amount);
        profile.insertRandomData();

        Group group = new Group(amount / 10);
        group.insertRandomData();

        GroupMembership groupMembership = new GroupMembership(amount);
        groupMembership.insertRandomData();

        Event event = new Event(amount);
        event.insertRandomData();

        EventMembership eventMembership = new EventMembership(amount);
        eventMembership.insertRandomData();

        FriendRelation friendRelation = new FriendRelation(amount);
        friendRelation.insertRandomData();

        WorkPlace workPlace = new WorkPlace(amount);
        workPlace.insertRandomData();

        WorkPlaceList workPlaceList = new WorkPlaceList(amount);
        workPlaceList.insertRandomData();

        School school = new School(amount);
        school.insertRandomData();

        SchoolList schoolList = new SchoolList(amount);
        schoolList.insertRandomData();

        Post post = new Post(amount);
        post.insertRandomData();

        PostList postList = new PostList(amount);
        postList.insertRandomData();

        ReceiverPost recivierPost = new ReceiverPost(amount);
        recivierPost.insertRandomData();

        long end_time = System.nanoTime();
        logger.info("Total operation time: "
                + (((end_time - start_time) / 1e9)) + " sec.");
    }

    /**
     * This method initialize database structure.
     */
    private static void initializeDatabaseStructure() {
        // TODO Auto-generated method stub
    }

    /**
     * Main method.
     * 
     * @param args
     *            jdbc:mysql://kulisz.eu:3306/mszczygiel_ZTBD?user=xxx&
     *            password=xxx
     * @throws SQLException
     */
    public static void main(final String... args) throws SQLException {
        if (args.length == 2) {
            MariaDBConnection.getInstance(args[0]);
            initializeDatabaseStructure();
            fillDatabase(Integer.parseInt(args[1]));
            MariaDBConnection.endConnection();
        } else {
            logger.warning("Connection string is missing or amount of insertion");
        }
    }
}
