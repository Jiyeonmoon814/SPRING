package DI_Annotation_02;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	//자동 주입 
	//setter를 통해서 recorder라는 객체의 주소를 삽입 
	//By Type 
	@Autowired
	@Qualifier("recorder_1") //<qualifier value="recorder_1"></qualifier>
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	@Autowired
	@Qualifier("recorder_2") 
	public void Gmethod(Recorder rec) {
		System.out.println("Gmethod(rec):"+rec);
	}
}
