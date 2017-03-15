import { Observable } from "rxjs";
import 'rxjs/add/operator/map';
import { OpaqueToken } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { Http } from "@angular/http";
import { AlertService } from "../bootstrap/alert.service";
export declare class MessageConfig {
    [id: string]: string;
}
export declare const MESSAGE_CONFIG: OpaqueToken;
export declare class Api {
    private http;
    private alert;
    private messages;
    constructor(http: Http, alert: AlertService, messages: MessageConfig);
    submit<T>(url: string): Observable<T>;
    submit<T>(url: string, form: FormGroup): Observable<T>;
    submit<T>(url: string, form: any): Observable<T>;
}
