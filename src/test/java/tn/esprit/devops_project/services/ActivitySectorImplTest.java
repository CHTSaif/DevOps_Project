package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.ActivitySector;
import tn.esprit.devops_project.repositories.ActivitySectorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ActivitySectorImplTest {

    @InjectMocks
    private ActivitySectorImpl activitySectorService;

    @Mock
    private ActivitySectorRepository activitySectorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllActivitySectors() {
        ActivitySector sector1 = new ActivitySector();
        ActivitySector sector2 = new ActivitySector();
        when(activitySectorRepository.findAll()).thenReturn(Arrays.asList(sector1, sector2));

        List<ActivitySector> result = activitySectorService.retrieveAllActivitySectors();

        assertEquals(2, result.size());
        verify(activitySectorRepository, times(1)).findAll();
    }

    @Test
    public void testAddActivitySector() {
        ActivitySector sector = new ActivitySector();
        when(activitySectorRepository.save(sector)).thenReturn(sector);

        ActivitySector result = activitySectorService.addActivitySector(sector);

        assertEquals(sector, result);
        verify(activitySectorRepository, times(1)).save(sector);
    }

    @Test
    public void testDeleteActivitySector() {
        Long id = 1L;
        doNothing().when(activitySectorRepository).deleteById(id);

        activitySectorService.deleteActivitySector(id);

        verify(activitySectorRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateActivitySector() {
        ActivitySector sector = new ActivitySector();
        when(activitySectorRepository.save(sector)).thenReturn(sector);

        ActivitySector result = activitySectorService.updateActivitySector(sector);

        assertEquals(sector, result);
        verify(activitySectorRepository, times(1)).save(sector);
    }

    @Test
    public void testRetrieveActivitySector() {
        Long id = 1L;
        ActivitySector sector = new ActivitySector();
        when(activitySectorRepository.findById(id)).thenReturn(Optional.of(sector));

        ActivitySector result = activitySectorService.retrieveActivitySector(id);

        assertEquals(sector, result);
        verify(activitySectorRepository, times(1)).findById(id);
    }

    @Test
    public void testRetrieveActivitySectorNotFound() {
        Long id = 1L;
        when(activitySectorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> activitySectorService.retrieveActivitySector(id));
        verify(activitySectorRepository, times(1)).findById(id);
    }
}