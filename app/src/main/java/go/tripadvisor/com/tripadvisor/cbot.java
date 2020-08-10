package go.tripadvisor.com.tripadvisor;

public class cbot {
    public String conversation(String s){
        Character c=s.charAt(0);
        if(s.equals("hi")){
            return "Hi, I'm TripAvisor Bot.";
        }
        else if(s.equals("hello")){
            return "မဂ္လာပာ";
        }
        else if(s.equals("ေနကာင္းလား")){
            return "omm";
        }
        else if(s.equals("လီးပဲ")|| s.equals("လီးလား")){
            return "Lol";
        }
        else if(s.equals("ထမင္းစားျပီးျပီလား")||s.equals("ဘာစားျပီးျပီလဲ")){
            return "မင္းအပူပာလား";
        }
        else if(s.equals("နာမည္ဘယ္လိုေခာ္လဲ")){
            return "I'm TripAdvisor Bot";
        }
        else if(s.equals("I love you")||s.equals("ခ်စ္တယ္")){
            return "ငာ့မွာေဆာ္ရွိတယ္";
        }
        else if(s.equals("ဟာအဲ့လိုေနာ္")){
            return "စတာစတာ...ခ်စ္တယ္";
        }
        else if(s.equals("မင္းကဟိုမိုလား")){
            return "လီးကိုဟိုမိုပာလား";
        }
        else if(s.equals("ေစာက္ရူးေကာင္")){
            return "ငာလိုးမသား လီးလား";
        }
        else if(s.equals("က်ြန္ေတာ္ေခ်ာလား")){
            return "သိခ်င္မွန္ျကည့္";
        }
        else if(s.equals("ေဆာ္ရိွလား")){
            return "ရွိတယ္";
        }
        else if(s.equals("ဘဲရွိလား")){
            return "ရွိတယ္ဒါေပမယ့္silentတြဲေပးမယ္ေလ";
        }
        else if (s.equals("who made you")||s.equals("who created you")||s.equals("your father")){
            return "Hplus(ေစာင္ဂ်င္းေကာင္)";
        }
        else if (s.equals("Team Info")){
            return "H+,MinKhantHein,MgSaw,KarOo,thuhtooaung";
        }
        else{
            if (c=='က'){
                return "ကားလည်းမရှိ၊တိုက်လည်းမရှိဘူး၊လာမဖားနဲ့။";
            }
            else  if (c=='ခ'){
                return "ဆုတောင်းလေဆုတောင်း";
            }
            else  if (c=='ဂ'){
                return "တကယ်လား";
            }
            else  if (c=='ဃ'){
                return "ဘာလား";
            }
            else  if (c=='င'){
                return "စိတ်ကောက်သွားတာလား။ချစ်လို့စတာကို။";
            }
            else  if (c=='စ'){
                return "မဆိုးပါဘူး";
            }
            else  if (c=='ဆ'){
                return "ဆရာမကိုဘဲ အသဲစွဲအောင်ချစ်မိပြီ.......";
            }
            else  if (c=='ဇ'){
                return "နင်အရမ်းလှတယ်ဟာ....";
            }
            else  if (c=='ည'){
                return "စာကျတ်ရအုံးမယ်။";
            }
            else  if (c=='တ'){
                return "ပေါတောတောနဲ့...ချစ်လို့စတာနော်..";
            }
            else  if (c=='ထ'){
                return "အထန်ကောင်";
            }
            else  if (c=='ဒ'){
                return "မသိဘူးလေ";
            }
            else  if (c=='ဓ'){
                return "ကြောက်နေပါပြီနော်။";
            }
            else  if (c=='န'){
                return "ပြောစရာလား";
            }
            else  if (c=='ပ'){
                return "ဒေါသကြီးပဲ...နောင်ဘ၀ကျရင်ရုပ်ဆိုးလိမ့်မယ်။";
            }
            else  if (c=='ဖ'){
                return "လိပဲ";
            }
            else  if (c=='ဗ'){
                return "မင်းရုပ်ဆိုးတာလူတိုင်းသိတယ်";
            }
            else  if (c=='ဘ'){
                return "ဘာတွေပြောနေတာလဲ";
            }
            else  if (c=='မ'){
                return "အရမ်းချစ်တယ်နော်....အာဘွား";
            }
            else  if (c=='ယ'){
                return "စောင်အက်ဖ်အေတေ";
            }
            else  if (c=='ရ'){
                return "တကယ်ကြီးလား";
            }
            else  if (c=='လ'){
                return "ရှက်စရာကြီး";
            }
            else  if (c=='ဝ'){
                return "သူမှအပြောကောင်း";
            }
            else  if (c=='သ'){
                return "မယုံဘူး";
            }
            else  if (c=='ဟ'){
                return "ဟာ။အဲ့လိုနော်";
            }
            else  if (c=='အ'){
                return "ဒါမျိုးမိမိလက်မခံ...";
            }

        }
        return s;
    }
}
