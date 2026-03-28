package shapes;

import fruits.Fruit;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;

public class CardFace extends MeshView {
	
	public CardFace(Fruit fruit) {
		
		super();
		
		TriangleMesh mesh = new TriangleMesh(VertexFormat.POINT_TEXCOORD);
		
		mesh.getPoints().addAll(
				-.75f, -1.25f, 0f,
				-.75f, 1.25f, 0f,
				.75f, 1.25f, 0f,
				.75f, -1.25f, 0f,
				-.75f, -1.5f, 0f,
				-.9f, -1.4f, 0f,
				-1f, -1.25f, 0f,
				-1f, 1.25f, 0f,
				-.9f, 1.4f, 0f,
				-.75f, 1.5f, 0f,
				.75f, 1.5f, 0f,
				.9f, 1.4f, 0f,
				1f, 1.25f, 0f,
				1f, -1.25f, 0f,
				.9f, -1.4f, 0f,
				.75f, -1.5f, 0f
				);
		
		for (int i = 0; i < mesh.getPoints().size() / 3; i++) {
			int x = i * 3;
			int y = x + 1;
			float u = (mesh.getPoints().get(x) + 1f) / 3f;
			float v = (mesh.getPoints().get(y) + 1.5f) / 3f;
			mesh.getTexCoords().addAll(u, v);
		}
		
		mesh.getFaces().addAll(
				0, 0, 1, 1, 2, 2,
				0, 0, 2, 2, 3, 3,
				0, 0, 4, 4, 5, 5,
				0, 0, 5, 5, 6, 6,
				0, 0, 6, 6, 7, 7,
				0, 0, 7, 7, 1, 1,
				1, 1, 7, 7, 8, 8,
				1, 1, 8, 8, 9, 9,
				1, 1, 9, 9, 10, 10,
				1, 1, 10, 10, 2, 2,
				2, 2, 10, 10, 11, 11,
				2, 2, 11, 11, 12, 12,
				2, 2, 12, 12, 13, 13,
				2, 2, 13, 13, 3, 3,
				3, 3, 13, 13, 14, 14,
				3, 3, 14, 14, 15, 15,
				3, 3, 15, 15, 4, 4,
				3, 3, 4, 4, 0, 0
				);
		
		super.setMesh(mesh);
		
		PhongMaterial mat = new PhongMaterial();
		if (fruit == null) {
			Image backface = new Image("/textures/backface.png");
			mat.setDiffuseMap(backface);
		} else {
			mat = fruit.getDepiction();
		}
		
		super.setMaterial(mat);

	}

}
