import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Router } from '@angular/router';
import 'rxjs/add/operator/toPromise';


// declare var swal: any;
@Injectable()
export class CommonService {

    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(
        private http: Http,
        private router: Router) { }


    getCallWebService(homesUrl: string): Promise<any> {
        var result = this.http.get(homesUrl, { headers: this.headers })
            .toPromise().then(response => response.json())
            .catch(this.handleError);
        return result;
    }

    postCallWebService(homesUrl: string, data: any): Promise<any> {
        var result = this.http.post(homesUrl, JSON.stringify(data), { headers: this.headers })
            .toPromise().then(response => response.json())
            .catch(this.handleError);
        return result;
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only

        return Promise.reject(error.message || error);
    }

}
