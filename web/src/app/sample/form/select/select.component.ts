import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { DefaultFormComponent } from "app/sample/form/index/index.component";
import { Observable } from "rxjs";
import { AlertService, ExtEnumService, ExtEnumData } from "calico";
import { ExtEnum } from "../../../../calico/core/ext-enum.service";

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styles: [`
    .large {
      width: 250px;
    }
  `]
})
export class SelectComponent extends DefaultFormComponent {
  constructor(
    alert: AlertService,
    extEnumService: ExtEnumService,
    private fb: FormBuilder,
  ) {
    super(alert, extEnumService);
  }

  private observable$: Observable<ExtEnum[]>;

  ngOnInit(): void {
    super.ngOnInit();

    this.observable$ = this.extEnumService.values('sex').map(data => data['sex']);
  }

  createForm(): Observable<FormGroup> {
    return this.extEnumService.values('sex', 'familyType').map((data: ExtEnumData) => {
      return this.fb.group({
        val1: [null],
        val2: [data['sex'][0]],
        val3: [null, Validators.required],
        val4: [data['familyType'][0]],
        val5: [null],
        val6: [data['familyType'][0]['NAME']],
        val7: [3],
        val8: [data['sex'][0]],
      });
    });
  }

}
