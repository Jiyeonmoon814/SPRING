package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	@Autowired
	//자동 주입 
	//setter를 통해서 recorder라는 객체의 주소를 삽입 
	//By Type 
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
}
