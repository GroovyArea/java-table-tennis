package com.tabletennis.core.room;

import com.tabletennis.core.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ParticipateRoomPolicyTest {

    @Mock
    private RoomReader roomReader;

    @Mock
    private Room room;

    @Mock
    private User user;

    private ParticipateRoomPolicy participateRoomPolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participateRoomPolicy = new ParticipateRoomPolicy(roomReader);
    }

    @Test
    void testIsValid_WhenRoomIsWaitAndUserIsActiveAndNotParticipatingAndRoomIsNotFull() {
        // Arrange
        when(room.isWait()).thenReturn(true);
        when(user.isActive()).thenReturn(true);
        when(roomReader.isUserParticipate(user.getId())).thenReturn(false);
        when(room.isFull()).thenReturn(false);

        // Act
        boolean result = participateRoomPolicy.isValid(room, user);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValid_WhenRoomIsNotWait() {
        // Arrange
        when(room.isWait()).thenReturn(false);

        // Act
        boolean result = participateRoomPolicy.isValid(room, user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenUserIsNotActive() {
        // Arrange
        when(room.isWait()).thenReturn(true);
        when(user.isActive()).thenReturn(false);

        // Act
        boolean result = participateRoomPolicy.isValid(room, user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenUserIsAlreadyParticipating() {
        // Arrange
        when(room.isWait()).thenReturn(true);
        when(user.isActive()).thenReturn(true);
        when(roomReader.isUserParticipate(user.getId())).thenReturn(true);

        // Act
        boolean result = participateRoomPolicy.isValid(room, user);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenRoomIsFull() {
        // Arrange
        when(room.isWait()).thenReturn(true);
        when(user.isActive()).thenReturn(true);
        when(roomReader.isUserParticipate(user.getId())).thenReturn(false);
        when(room.isFull()).thenReturn(true);

        // Act
        boolean result = participateRoomPolicy.isValid(room, user);

        // Assert
        assertFalse(result);
    }
}
