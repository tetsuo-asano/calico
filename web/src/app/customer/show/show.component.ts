import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";

import { MainService, Record } from "../main.service";
import { AlertService, SearchService } from "calico";

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
})
export class ShowComponent implements OnInit {

  constructor(
    private mainService: MainService,
    private route: ActivatedRoute,
    private router: Router,
    private alert: AlertService,
    private searchService: SearchService,
  ) { }

  record: Record;
  fragment: string;

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.mainService.getRecord(params['id']).subscribe(record => {
        this.record = record;
      });
    });
    this.fragment = this.searchService.restoreAsFragment('customer/index');
  }

  delete(): void {
    if(!confirm('削除してもよろしいですか？')) return;
    this.mainService.delete(this.record.id).subscribe(() => {
      this.alert.success('削除しました。');
      this.router.navigate(['../index'], {relativeTo: this.route});
    });
  }

}
