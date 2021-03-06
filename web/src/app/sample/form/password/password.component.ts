import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { DefaultFormComponent } from "app/sample/form/index/index.component";
import { Observable } from "rxjs";
import { AlertService, ExtEnumService } from "calico";

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styles: [`
    .large {
      width: 250px;
    }
  `]
})
export class PasswordComponent extends DefaultFormComponent {
  constructor(
    alert: AlertService,
    extEnumService: ExtEnumService,
    private fb: FormBuilder,
  ) {
    super(alert, extEnumService);
  }

  createForm(): Observable<FormGroup> {
    return Observable.of(this.fb.group({
      val1: [null],
      val2: ['ABC'],
      val3: [null],
      val4: [null, Validators.required],
    }));
  }

}
