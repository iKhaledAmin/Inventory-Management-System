package com.khaledamin.ims.media.image.domain.model;

import com.khaledamin.ims.core.audit.AuditableEntity;
import com.khaledamin.ims.media.image.domain.generator.ImageCodeGenerator;
import com.khaledamin.ims.media.image.exception.ImageTechnicalException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "images")
public class Image extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "code", nullable = false, updatable = false, unique = true)
    private String code;

    /**
     * Original uploaded file name.
     * Example:iphone-15-front.jpg
     */
    @Column(name = "image_name", nullable = false)
    private String imageName;


    /**
     * SHA-256 checksum of the original uploaded image.
     * Used for:
     * - Duplicate detection
     * - Integrity validation
     * - Future storage migration verification
     */
    @Column(
            name = "checksum",
            nullable = false,
            updatable = false,
            length = 64,
            comment = "SHA-256 checksum of original image"
    )
    private String checksum;

    // -------------------------------------------- Relations -------------------------------------- //

    @Builder.Default
    @OneToMany(
            mappedBy = "image",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ImageVariant> variants = new ArrayList<>();

    // ------------------------------------------ End Relations ------------------------------------ //


    // -------------------------------------------- Methods ---------------------------------------- //

    public static Image create(MultipartFile file) {

        String imageName = file.getOriginalFilename();
        String checksum = generateChecksum(file);
        String code = ImageCodeGenerator.generate();

        return Image.builder()
                .code(code)
                .imageName(imageName)
                .checksum(checksum)
                .build();
    }

    public void addVariant(ImageVariant variant) {
        if (variant == null){
            throw ImageTechnicalException.nullImageVariant();
        }

        variant.attachTo(this);
        variants.add(variant);
    }


    public ImageVariant getVariant(ImageResolution resolution) {
        if (resolution == null){
            throw ImageTechnicalException.nullImageResolution();
        }

        return variants.stream()
                .filter(v -> v.matches(resolution))
                .findFirst()
                .orElse(null);
    }

    public String getVariantStorageKey(ImageResolution resolution){
        if (resolution == null){
            throw ImageTechnicalException.nullImageResolution();
        }

       return getVariant(resolution).getStorageKey();
    }

    public ImageVariant getOriginalVariant() {
        return getVariant(ImageResolution.ORIGINAL);
    }

    public boolean hasVariant(ImageResolution resolution) {
        if (resolution == null){
            throw ImageTechnicalException.nullImageResolution();
        }

        return variants.stream()
                .anyMatch(v -> v.getResolution() == resolution);
    }


    public boolean hasSameContent(MultipartFile file){
        return this.getChecksum().equals(generateChecksum(file));
    }

    public boolean hasSameIdentity(Image image){
        return this.getCode().equals(image.getCode());
    }

    private static String generateChecksum(MultipartFile file) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(file.getBytes());

            return HexFormat.of().formatHex(hash);

        } catch (Exception exception) {
            throw ImageTechnicalException.checksumGenerationFailed(exception);
        }
    }

    // ------------------------------------------ End Methods -------------------------------------- //
}