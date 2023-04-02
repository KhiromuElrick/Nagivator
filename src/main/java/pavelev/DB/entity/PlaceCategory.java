package pavelev.DB.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "place_categories", schema = "navigatordb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceCategory extends Model {
    @NotNull
    @Column(name = "title",nullable = false)
    private String title;

    @OneToMany(mappedBy = "placeCategory")
    private Set<Place> places = new HashSet<>();

}
