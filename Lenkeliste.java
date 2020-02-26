//Lenkeliste tar inn en type og implementerer interfacet Liste som også tar inn en type(String, int)
// Velger å bruke enveisliste ettersom ... (forklar)
class Lenkeliste <T> implements Liste<T> {
  //Oppretter en indre klasse Node som brukes internt
  class Node {
    //referanse til neste Node objekt
    Node neste = null;
    //Variabel av en type
    T data;
    //Konstruktør som tar inn en type
    Node(T x) {
      data = x;
    }
  }

  //Ny node referanse utenfor Node klassen
  private Node start = null;

  //returnerer størrelsen på listen
  public int stoerrelse() {
    //referanse til start
    Node p = start;
    //teller
    int antallNoder = 0;
    //så lenge referansen ikke er tom
    while(p != null) {
      //legg til 1 på teller og gjør p om til neste referansen sin
      antallNoder++;
      p = p.neste;
    }
    return antallNoder;
  }

  //Setter inn et element på slutten av listen
  public void leggTil(T x)  {
    //Dersom instansvariabelen start for klassen lenkeliste er tom(som betyr at listen er tom)
    if(this.start == null) {
      //start får verdi til node1
      start = new Node(x);
    }
    else {
      //Lager en referanse til start
      Node p = start;
      //Så lenge det første elementet i listen sin neste ikke er tom
      while(p.neste != null) {
        //referansen til start får nå verdi til neste node i køen
        p = p.neste;
      }
      //Om while løkken ikke gjelder får start referansen sin neste verdi til det nye elementet
      p.neste = new Node(x);
    }
  }

  //Legger inn et nytt element i listen og skyver neste element et hakk lenger bak
  public void leggTil(int pos, T x) throws UgyldigListeIndeks {
    //hvis listen er tom og indeks ikke er 0
    if(this.start == null && pos != 0) {
      throw new UgyldigListeIndeks(pos-1);
    }
    //eller hvis listen ikke er tom, men indeksen er ugyldig
    else if (this.start != null && pos < 0 || pos > this.stoerrelse()) {
      throw new UgyldigListeIndeks(pos);
    }
    //går dersom listen ikke er tom og indeks er gyldig, men også dersom listen er tom og indeks er 0
    else {
      //Referanse som holder på første node i listen
      Node p = start;
      //ny node
      Node ny = new Node(x);
      //Så lenge posisjonen ikke er første indeks
      if(pos != 0) {
        for(int i = 0; i < pos-1; i++) {
          //referansen tmp får verdien noden som er på posisjonen satt som argument
          p = p.neste;
        }
        //p sin neste flyttes til den nye noden sin neste
        ny.neste = p.neste;
        //den nye noden sin neste flyttes til p sin neste
        p.neste = ny;
      }
      else {
        //nyNode sin neste blir tmp og start blir den nye noden
        ny.neste = p;
        start = ny;
      }
    }
  }

  //Setter inn et element på en gitt posisjon og overskriver det som var der fra før
  public void sett(int pos, T x) throws UgyldigListeIndeks {
    if(this.start == null) {
      throw new UgyldigListeIndeks(pos-1);
    }
    else {
      if(pos < 0 || pos >= this.stoerrelse()) {
        throw new UgyldigListeIndeks(pos);
      }
      else {
        //Referanse til første element i listen
        Node p = start;
        //Går så lenge telleren er lavere enn posisjonen
        for(int i = 0; i < pos; i++) {
          //Referansen får verdi til
          p = p.neste;
        }
        //tmp sin data blir overskrivet med data til ny node
        p.data = x;
      }
    }
  }

  //Henter ut element på oppgitt indeks
  public T hent(int pos) throws UgyldigListeIndeks {
    if(this.start == null) {
      throw new UgyldigListeIndeks(pos-1);
    }
    else {
      if(pos < 0 || pos >= this.stoerrelse()) {
        throw new UgyldigListeIndeks(pos);
      }
      else {
        Node p = start;
        for(int i = 0; i < pos; i++) {
          p = p.neste;
        }
        return p.data;
      }
    }
  }

  //fjerner på gitt indeks i listen
  public T fjern(int pos) throws UgyldigListeIndeks {
    //hvis listen er tom
    if(this.start == null) {
      throw new UgyldigListeIndeks(pos-1);
    }
    else {
      //om elementet som skal fjernes er det første i listen, kalles det på metoden som fjerner det første elementet
      if(pos == 0) {
        return this.fjern();
      }
      //Om indeksen oppgit er negativ eller høyere eller lik størrelsen på listen
      else if(pos < 0 || pos >= this.stoerrelse()) {
        //kaster unntak med pos som argument
        throw new UgyldigListeIndeks(pos);
      }
      else {
        Node p = start;
        //teller til indeks på posisjon
        for(int i = 0; i < pos-1; i++) {
          p = p.neste;
        }
        //sparer på elementet som skal fjernes
        Node tmp = p.neste;
        //elementet erstattes med neste element
        p.neste = tmp.neste;
        //returnerer det fjernede elementet
        return tmp.data;
      }
    }
  }


  //Fjerner og returnerer elementet på starten av listen
  public T fjern() throws UgyldigListeIndeks {
    //Sjekker om listen er tom
    if(this.start == null) {
      //Kaster nytt unntak med indeks -1 (i dette tilfellet er indeks 0 ettersom metoden skal fjerne første indeks i listen)
      throw new UgyldigListeIndeks(0-1);
    }
    else {
      //Referanse til første element i listen
      Node tmp = start;
      //Første element i listen blir neste element i køen
      this.start = start.neste;
      //returnerer det første elementet
      return tmp.data;
    }
  }
}
