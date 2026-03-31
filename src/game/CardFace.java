package game;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;

public class CardFace extends MeshView {
	
	public CardFace(Pattern pattern) {
		
		super();
		
		TriangleMesh mesh = new TriangleMesh(VertexFormat.POINT_TEXCOORD);
		
		mesh.getPoints().addAll(
				-1.0f, -1.5f, 0.0f, //0
				-1.0f, 1.5f, 0.0f, //1
				1.0f, 1.5f, 0.0f, //2
				1.0f, -1.5f, 0.0f); //3
		
		mesh.getTexCoords().addAll(
				0.0f, 0.0f, //top left
				0.0f, 1.0f, //bottom left
				2.0f / 3.0f, 1.0f, //bottom right
				2.0f / 3.0f, 0.0f //top right
				);
		
		mesh.getFaces().addAll(
				0, 0, 1, 1, 2, 2,// 012
				0, 0, 2, 2, 3, 3 // 023
				);
		
		super.setMesh(mesh);
		
		PhongMaterial mat = new PhongMaterial();
		Image diffuse;
		if (pattern == null) {
			diffuse = new Image("/textures/diffuse_back.png");
			
		} else {
			diffuse = pattern.getDiffuse();
		}
		mat.setDiffuseMap(diffuse);
		Image specular = new Image("/textures/specular.png");
		mat.setSpecularMap(specular);
		Image bump = new Image("/textures/bump.png");
		mat.setBumpMap(bump);
		
		super.setMaterial(mat);

	}

}
