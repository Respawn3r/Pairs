package game;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import javafx.scene.transform.Rotate;

public class Grass extends MeshView {

	private Rotate pitch;
	private Rotate yaw;
	
	public Grass() {
		
		super();
		
		TriangleMesh mesh = new TriangleMesh(VertexFormat.POINT_TEXCOORD);
		
		mesh.getPoints().addAll(
				-3.0f, -1.0f, 0.0f, //0
				-3.0f, 1.0f, 0.0f, //1
				3.0f, 1.0f, 0.0f, //2
				3.0f, -1.0f, 0.0f //3
				); //width 6,height 2
		
		mesh.getTexCoords().addAll(
				0.0f, 0.0f, //left top
				0.0f, 2.0f / 6.0f, //left bottom
				1.0f, 2.0f / 6.0f, //right bottom
				1.0f, 0.0f // right top
				);
		
		mesh.getFaces().addAll(
				0, 0, 1, 1, 2, 2,// 012
				0, 0, 2, 2, 3, 3 // 023
				);
		
		super.setMesh(mesh);
		
		//FIRST CREATE THOSE MAPS!
		/*PhongMaterial mat = new PhongMaterial();
		Image diffuse = new Image("/textures/grass_diffuse.png");
		mat.setDiffuseMap(diffuse);
		Image specular = new Image("/textures/grass_specular.png");
		mat.setSpecularMap(specular);
		super.setMaterial(mat);*/
		
		
		this.pitch = new Rotate();
		this.pitch.setAxis(Rotate.X_AXIS);
		this.yaw = new Rotate();
		this.yaw.setAxis(Rotate.Z_AXIS);
		
	}

	public void setPitch(float pitch) {
		this.pitch.setAngle(pitch);
		return;
	}

	public void setYaw(float yaw) {
		this.yaw.setAngle(yaw);
		return;
	}

	public void shake(float shakePos, float shakePitch, float shakeYaw) {
		
		float xDelta = StaticRandom.random.nextFloat(shakePos * 2.0f) 
				- shakePos;
		float yDelta = StaticRandom.random.nextFloat(shakePos * 2.0f) 
				- shakePos;
		this.setTranslateX(this.getTranslateX() + xDelta);
		this.setTranslateY(this.getTranslateY() + yDelta);
		
		float pitchDelta = StaticRandom.random.nextFloat(shakePitch * 2.0f) 
				- shakePitch;
		this.pitch.setAngle(this.pitch.getAngle() + pitchDelta);
		float yawDelta = StaticRandom.random.nextFloat(shakeYaw * 2.0f) 
				- shakeYaw;
		this.yaw.setAngle(this.yaw.getAngle() + yawDelta);
		
		return;
		
	}

}
