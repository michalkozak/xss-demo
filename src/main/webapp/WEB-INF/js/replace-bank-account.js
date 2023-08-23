function replaceBankAccount() {
  console.log("I'm hacking the site");
  let demoDetailsElement = window.document.querySelector('.demo-details');
  if (demoDetailsElement && demoDetailsElement.firstElementChild) {
    let hackedBankAccountElement = window.document.createElement('p');
    hackedBankAccountElement.innerHTML = "<strong>bank account:</strong> PL18 2039 0045 1130 1602 1547 0591";
    demoDetailsElement.firstElementChild.replaceWith(hackedBankAccountElement);
  }
}

replaceBankAccount();
