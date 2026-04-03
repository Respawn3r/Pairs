package game;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;

public class Mark extends MeshView {
	
	public Mark(String name) {
		
		super();
		
		TriangleMesh mesh = new TriangleMesh(VertexFormat.POINT_TEXCOORD);
		
		mesh.getPoints().addAll(
				-1.0f, -1.5f, 0.0f, //0
				-1.0f, 1.5f, 0.0f, //1
				1.0f, 1.5f, 0.0f, //2
				1.0f, -1.5f, 0.0f //3
				); 
		
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
		Image diffuse = new Image("/textures/mark_" + name + ".png");
		mat.setDiffuseMap(diffuse);
		
		super.setMaterial(mat);
		
	}

}