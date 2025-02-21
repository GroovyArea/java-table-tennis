package com.tabletennis.core.room;

import com.tabletennis.core.user.RoomUsers;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserGetter;
import com.tabletennis.core.user.vo.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CreateRoomPolicyTest {

    @Mock
    private UserGetter userGetter;

    @Mock
    private RoomUsers roomUsers;

    @Mock
    private User user;

    private CreateRoomPolicy createRoomPolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createRoomPolicy = new CreateRoomPolicy(userGetter, roomUsers);
    }

    @Test
    void testIsAble_WhenUserIsActiveAndDoesNotHaveRoom() {
        // Arrange
        long userId = 1L;
        when(userGetter.get(userId)).thenReturn(user);
        when(user.getStatus()).thenReturn(UserStatus.ACTIVE);
        when(roomUsers.hasRoom(userId)).thenReturn(false);

        // Act
        boolean result = createRoomPolicy.isAble(userId);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsAble_WhenUserIsNotActive() {
        // Arrange
        long userId = 1L;
        when(userGetter.get(userId)).thenReturn(user);
        when(user.getStatus()).thenReturn(UserStatus.NON_ACTIVE);
        when(roomUsers.hasRoom(userId)).thenReturn(false);

        // Act
        boolean result = createRoomPolicy.isAble(userId);

        // Assert
        assertFalse(result);
    }
}
