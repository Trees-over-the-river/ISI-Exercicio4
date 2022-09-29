import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVision;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageAnalysis;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ImageTag;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.VisualFeatureTypes;

import java.util.List;

public class MyIA {

    private static String key = "826b63958b41445a90f0924093534750";
    private static String endpoint = "https://visao-computacional-azure-exercicio-4.cognitiveservices.azure.com/";

    public static void main(String[] args) {

        ComputerVisionClient computerVisionClient = ComputerVisionManager.authenticate(key).withEndpoint(endpoint);

        for(int i = 1; i <= 5; i++) {
            System.out.println("\n\nAnalizando a imagem " + i + ":\n");
            analyzeImage( "https://source.unsplash.com/random/400*400", computerVisionClient.computerVision());
        }

    }

    public static void analyzeImage(String img, ComputerVision client) {
        try {
            ImageAnalysis analysis = client.analyzeImage().withUrl(img)
                    .withVisualFeatures(List.of(VisualFeatureTypes.TAGS)).execute();

            for (ImageTag tag : analysis.tags()) 
                System.out.println("- " + tag.name() + " (" + String.format("%.2f",tag.confidence()*100) + "%)");


            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}