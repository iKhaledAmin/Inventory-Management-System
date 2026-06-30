package com.khaledamin.ims.media.image.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.media.image.application.model.GeneratedImageVariant;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "image_variants",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"image_id", "resolution"}
                )
        }
)
public class ImageVariant extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_variant_id")
    private Long id;

    /**
     * Logical storage key.
     * Examples:
     * product/IMG_01JTXABC/original.webp
     * product/IMG_01JTXABC/thumbnail.webp
     * category/IMG_01JTXXYZ/original.webp
     * profile/IMG_01JTXXYZ/large.webp
     */
    @Column(name = "storage_key", nullable = false,updatable = false,unique = true)
    private String storageKey;

    /**
     * Actual stored width.
     */
    @Column(name = "width", nullable = false,updatable = false)
    private int width;

    /**
     * Actual stored height.
     */
    @Column(name = "height", nullable = false,updatable = false)
    private int height;

    /**
     * Variant file size in bytes.
     */
    @Column(name = "file_size", nullable = false,updatable = false)
    private long fileSize;

    /**
     * MIME type of the stored variant.
     * Example:
     * image/webp
     */
    @Column(name = "mime_type", nullable = false,updatable = false)
    private String mimeType;

    /**
     * Generated resolution profile.
     * Examples:
     * ORIGINAL
     * LARGE
     * MEDIUM
     * SMALL
     * SQUARE_THUMBNAIL
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "resolution", nullable = false, updatable = false)
    private ImageResolution resolution;

    // -------------------------------------------- Relations -------------------------------------- //

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id", nullable = false, updatable = false)
    private Image image;

    // ------------------------------------------ End Relations ------------------------------------ //


    // -------------------------------------------- Methods ---------------------------------------- //

    public static ImageVariant create(
            String storageKey,
            GeneratedImageVariant generatedVariant,
            ImageResolution resolution
    ) {

        return ImageVariant.builder()
                .storageKey(storageKey)
                .width(generatedVariant.width())
                .height(generatedVariant.height())
                .fileSize(generatedVariant.fileSize())
                .mimeType(generatedVariant.mimeType())
                .resolution(resolution)
                .build();
    }



    void attachTo(Image image) {
        this.image = image;
    }

    public boolean isOriginal() {
        return resolution == ImageResolution.ORIGINAL;
    }

    public boolean matches(ImageResolution resolution) {
        return this.resolution == resolution;
    }

    // ------------------------------------------ End Methods -------------------------------------- //
}