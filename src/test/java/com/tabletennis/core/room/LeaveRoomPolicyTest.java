package com.tabletennis.core.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveRoomPolicyTest {

    @InjectMocks // ✅ LeaveRoomPolicy에 Mock 주입
    private LeaveRoomPolicy leaveRoomPolicy;

    @Mock
    private Room room;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsValid_WhenRoomAllowsLeave_AndUserJoined() {
        // Arrange
        long userId = 1L;
        when(room.canLeave()).thenReturn(true);
        when(room.isUserJoined(userId)).thenReturn(true);

        // Act
        boolean isValid = leaveRoomPolicy.isValid(userId, room);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void testIsValid_WhenRoomDoesNotAllowLeave() {
        // Arrange & Act
        long userId = 1L;
        when(room.canLeave()).thenReturn(false);

        // Assert
        assertFalse(leaveRoomPolicy.isValid(userId, room));
    }
}
