package pavelev.DB.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "places", schema = "navigatordb")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Place extends Model{
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    @NotNull
    @Column(name = "coordinateX", nullable = false)
    private Double cX;

    @NotNull
    @Column(name = "coordinateY", nullable = false)
    private Double cY;

    @ManyToOne
    @JoinColumn(name = "place_category_id",referencedColumnName = "id")
    private PlaceCategory placeCategory;

}
