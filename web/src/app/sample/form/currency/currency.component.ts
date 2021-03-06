import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { DefaultFormComponent } from "app/sample/form/index/index.component";
import { Observable } from "rxjs/Observable";
import { AlertService, ExtEnumService } from "calico";

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styles: [`
    .large {
      width: 250px;
    }
  `]
})
export class CurrencyComponent extends DefaultFormComponent {
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
      val2: [12345],
      val3: [null],
      val4: [null, Validators.required],
      val5: [-12345]
    }));
  }

}
