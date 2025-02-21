package com.tabletennis.core.room;

import com.tabletennis.core.room.exception.RoomIsFullException;
import com.tabletennis.core.room.vo.UserRoomTeams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SelectRoomTeamSpecificationTest {

    @Mock
    private Room room;

    private SelectRoomTeamSpecification selectRoomTeamSpecification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        selectRoomTeamSpecification = new SelectRoomTeamSpecification();
    }

    @Test
    void testSelect_WhenRedTeamIsNotFull() {
        // Arrange
        when(room.isFull(UserRoomTeams.RED)).thenReturn(false);
        when(room.isFull(UserRoomTeams.BLUE)).thenReturn(true);

        // Act
        UserRoomTeams result = selectRoomTeamSpecification.select(room);

        // Assert
        assertEquals(UserRoomTeams.RED, result);
    }

    @Test
    void testSelect_WhenBlueTeamIsNotFull() {
        // Arrange
        when(room.isFull(UserRoomTeams.RED)).thenReturn(true);
        when(room.isFull(UserRoomTeams.BLUE)).thenReturn(false);

        // Act
        UserRoomTeams result = selectRoomTeamSpecification.select(room);

        // Assert
        assertEquals(UserRoomTeams.BLUE, result);
    }

    @Test
    void testSelect_WhenBothTeamsAreFull() {
        // Arrange
        when(room.isFull(UserRoomTeams.RED)).thenReturn(true);
        when(room.isFull(UserRoomTeams.BLUE)).thenReturn(true);

        // Act & Assert
        assertThrows(RoomIsFullException.class, () -> selectRoomTeamSpecification.select(room));
    }
}
