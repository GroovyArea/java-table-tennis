package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CapacityRoomPolicyTest {

    @Mock
    private Room room;

    @Mock
    private UserRoomTeams targetTeam;

    @Mock
    private UserRoom userRoom1;

    @Mock
    private UserRoom userRoom2;

    private CapacityRoomPolicy capacityRoomPolicy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        capacityRoomPolicy = new CapacityRoomPolicy();
    }

    @Test
    void testIsValid_WhenTargetTeamSizeIsHalfOfRoomCapacity() {
        // Arrange
        int roomCapacity = 10;

        when(room.getCapacity()).thenReturn(roomCapacity);
        when(room.getUserRooms()).thenReturn(Arrays.asList(userRoom1, userRoom1, userRoom1, userRoom1, userRoom1));
        when(userRoom1.getUserRoomTeams()).thenReturn(targetTeam);

        // Act
        boolean result = capacityRoomPolicy.isValid(room, targetTeam);

        // Assert
        assertFalse(result);
    }

    @Test
    void testIsValid_WhenTargetTeamSizeIsNotHalfOfRoomCapacity() {
        // Arrange
        int roomCapacity = 10;
        when(room.getCapacity()).thenReturn(roomCapacity);
        when(room.getUserRooms()).thenReturn(Arrays.asList(userRoom1, userRoom1, userRoom1, userRoom1));
        when(userRoom1.getUserRoomTeams()).thenReturn(targetTeam);

        // Act
        boolean result = capacityRoomPolicy.isValid(room, targetTeam);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValid_WhenTargetTeamSizeIsMoreThanHalfOfRoomCapacity() {
        // Arrange
        int roomCapacity = 10;
        when(room.getCapacity()).thenReturn(roomCapacity);
        when(room.getUserRooms()).thenReturn(Arrays.asList(userRoom1, userRoom1, userRoom1, userRoom1, userRoom1, userRoom1));
        when(userRoom1.getUserRoomTeams()).thenReturn(targetTeam);

        // Act
        boolean result = capacityRoomPolicy.isValid(room, targetTeam);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsValid_WhenTargetTeamSizeIsZero() {
        // Arrange
        int roomCapacity = 10;
        when(room.getCapacity()).thenReturn(roomCapacity);
        when(room.getUserRooms()).thenReturn(List.of());

        // Act
        boolean result = capacityRoomPolicy.isValid(room, targetTeam);

        // Assert
        assertTrue(result);
    }
}
