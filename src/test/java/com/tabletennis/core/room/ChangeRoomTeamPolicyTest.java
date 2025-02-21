package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import com.tabletennis.core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChangeRoomTeamPolicyTest {

    @Mock
    private CapacityRoomPolicy capacityRoomPolicy;

    @Mock
    private Room room;

    @Mock
    private User user;

    private ChangeRoomTeamPolicy changeRoomTeamPolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        changeRoomTeamPolicy = new ChangeRoomTeamPolicy(capacityRoomPolicy);
    }

    @Test
    void testIsValid_WhenRoomIsWaitAndUserIsJoinedAndCapacityIsValid() {
        // Arrange
        UserRoomTeams userRoomTeams = UserRoomTeams.RED;
        when(room.isWait()).thenReturn(true);
        when(room.isUserJoined(user.getId())).thenReturn(true);
        when(capacityRoomPolicy.isValid(room, userRoomTeams)).thenReturn(true);

        // Act
        boolean result = changeRoomTeamPolicy.isValid(room, user, userRoomTeams);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValid_WhenRoomIsNotWait() {
        // Arrange
        UserRoomTeams userRoomTeams = UserRoomTeams.RED;
        when(room.isWait()).thenReturn(false);
        when(room.isUserJoined(user.getId())).thenReturn(true);
        when(capacityRoomPolicy.isValid(room, userRoomTeams)).thenReturn(true);

        // Act
        boolean result = changeRoomTeamPolicy.isValid(room, user, userRoomTeams);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenUserIsNotJoined() {
        // Arrange
        UserRoomTeams userRoomTeams = UserRoomTeams.RED;
        when(room.isWait()).thenReturn(true);
        when(room.isUserJoined(user.getId())).thenReturn(false);
        when(capacityRoomPolicy.isValid(room, userRoomTeams)).thenReturn(true);

        // Act
        boolean result = changeRoomTeamPolicy.isValid(room, user, userRoomTeams);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenCapacityIsNotValid() {
        // Arrange
        UserRoomTeams userRoomTeams = UserRoomTeams.RED;
        when(room.isWait()).thenReturn(true);
        when(room.isUserJoined(user.getId())).thenReturn(true);
        when(capacityRoomPolicy.isValid(room, userRoomTeams)).thenReturn(false);

        // Act
        boolean result = changeRoomTeamPolicy.isValid(room, user, userRoomTeams);

        // Assert
        assertFalse(result);
    }
}
