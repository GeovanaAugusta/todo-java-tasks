package br.com.geovanaaugusta.itau.tarefasservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

/**
 * Serviço responsável por fazer o upload de arquivos para o Amazon S3.
 */
@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * Construtor do serviço S3 que inicializa o cliente do Amazon S3.
     *
     * @param accessKeyId      Chave de acesso da AWS.
     * @param secretAccessKey  Chave secreta da AWS.
     * @param region           Região AWS onde o bucket está localizado.
     */
    public S3Service(@Value("${aws.accessKeyId}") String accessKeyId,
                     @Value("${aws.secretAccessKey}") String secretAccessKey,
                     @Value("${aws.region}") String region) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .httpClient(ApacheHttpClient.builder().build())
                .build();
    }

    /**
     * Faz o upload de um arquivo para o bucket S3.
     *
     * @param file o arquivo a ser carregado
     * @return a URL pública do arquivo carregado no S3
     * @throws IOException se ocorrer um erro ao ler o arquivo
     */
    public String uploadFile(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(uniqueFileName)
                .acl("public-read")
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        return "https://" + bucketName + ".s3.amazonaws.com/" + uniqueFileName;
    }
}
