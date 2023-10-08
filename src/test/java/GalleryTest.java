import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GalleryTest {

    private Gallery<Unit> GALLERY = new Gallery<>();

    @BeforeEach
    public void init() {
        GALLERY = new Gallery<>();
    }

    @Test
    @DisplayName("add unit in gallery")
    public void addPositiveTest() {
        Unit unit = new Unit(1);

        GALLERY.add(unit);

        assertThat(GALLERY.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("add null in gallery")
    public void addNullNegativeTest() {
        assertThatThrownBy(()-> {
            GALLERY.add(null);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("add unit with bad index")
    public void addBadIndexNegativeTest() {
        Unit unit = new Unit(1);

        assertThatThrownBy(()-> {
            GALLERY.add(Integer.MAX_VALUE, unit);
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("get unit")
    public void getUnitPositiveTest() {
        Unit unit = new Unit(1);

        GALLERY.add(0, unit);

        assertThat(GALLERY.get(0)).isEqualTo(unit);
    }

    @Test
    @DisplayName("get unit not found index")
    public void getUnitNegativeTest() {
        assertThatThrownBy(()-> {
            GALLERY.get(-1);
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("is empty")
    public void isEmptyTest() {
        assertThat(GALLERY.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("is not empty")
    public void isNotEmptyTest() {
        GALLERY.add(new Unit(2));
        assertThat(GALLERY.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("remove")
    public void removePositiveTest() {
        GALLERY.add(0, new Unit(2));

        GALLERY.remove(0);

        assertThat(GALLERY.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("remove not found unit")
    public void removeNegativeTest() {
        assertThatThrownBy(()->{
            GALLERY.remove(-1);
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

}