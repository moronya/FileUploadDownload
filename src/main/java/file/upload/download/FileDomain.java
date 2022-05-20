package file.upload.download;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_uploads")
public class FileDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private Long fileSize;
    private String downloadURI;
}
