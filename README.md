<h1>Sistema Eleitoral</h1>

<p>Sistema em java para simular uma urna eletrônica em um processo de votação.</p>
<ul>
  <li>Criar um sistema modelo de uma urna eletrônica.</li>
  <li>O sistema deve ser capaz de trabalhar com diferentes tipos de votação (presidencial, municipal,...), desde que respeitem o formato exigido.</li>
  <li>O sistema deve oferecer uma interface gráfica para o mesário e para o eleitor.</li>
  <li>Ao final do processo de votação, o sistema deverá apurar os resultados e informar quantos ganharam.</li>
</ul>

<strong>Em desenvolvimento...</strong>

<h1>Manual</h1>
<br>
<h2>Informações necessárias para o uso</h2>

<p>O programa é comandado por um mesário, este tem o objetivo de iniciar um processo de votação, liberar votos e finalizar o processo.</p>
<p>Cada processo só pode ser iniciado se não houver nenhum outro ocorrendo.</p>
<p>Ao liberar um processo de votação, que só pode ocorrer se houver algum processo de votação acontecendo, o mesário deverá informar o título
	de eleitor do eleitor e checar se o mesmo está apresentando informações corretas e se o mesmo vota na seção.</p>
<p>Após isso, o eleitor deverá realizar seu voto.</p>
<p>Ao término dos votos, o mesário retoma o acesso ao sistema.</p>

<ul>
	<h3>Cadastro de Mesário</h3>
	<li>Para cadastrar um novo usuário mesário é necessário informar os campo "login" e "senha" no arquivo de texto localizado no diretório:
		...SistemaEleitoral/Arquivos/Mesário/Mesários.txt;
	</li>
</ul>
<ul>
	<h3>Cadastro de Eleição</h3>
	<li>Para cadastrar uma nova eleição é necessário informar os campo "titulo", referente ao nome do cargo, "vice", onde sem valor indica 
		que não tem vice, "digitos", que informa a quantidade de digitos utilizados para votar neste cargo (exemplo: 2 digitos para voto de 
		presidente) e "eleitos", que informa a quantidade de individuos que poderão ganhar neste cargo. Estas informações deverão ser inseridas no 
		arquivo de texto localizado no diretório: 
		...SistemaEleitoral/Arquivos/Eleição/"...".txt;
		(onde ... é para substituir pelo nome do arquivo de eleição).
	</li>
</ul>
<ul>
	<h3>Cadastro de Eleitor</h3>
	<li>Para cadastrar um novo eleitor é necessário informar os campo "nome" e "titulo" no arquivo de texto localizado no diretório: 
		...SistemaEleitoral/Arquivos/Eleitores/"...".txt;
		(onde ... é para substituir pelo nome do arquivo de eleição).
	</li>
</ul>
<ul>
	<h3>Cadastro de Candidato</h3>
	<li>Para cadastrar um novo candidato é necessário informar os campo "nome", referente ao nome do candidato, "titulo", referente ao número do
		título de eleitor do candidato, "cargo", referente ao cargo do candidato (importante lembrar que o cargo deverá estar cadastrado no arquivo 
		de eleição no campo "titulo" fazendo correspondência ao cargo do candidato), "numero", que é referente ao número de voto do candidato, 
		"partido", que referencia o partido do candidato, "vice", caso haja vice (quando o campo não estiver vazio), faz referencia ao vice do candidato
		caso exista (importante lembrar que caso não exista, deixar o campo vice vazio), "partidovice", que faz referência ao partido do vice, caso exista,
		caso não exista, deixar o campo vazio, e, por último, "votos", que faz referência a quantidade de votos do candidato (deixar zerado, pois não é utilizado
		no momento inicial de votação). Ambos os dados deverão estar localizados, no arquivo de texto, no diretório: 
		...SistemaEleitoral/Arquivos/Candidatos/"...".txt;
		(onde ... é para substituir pelo nome do arquivo de eleição).
	</li>
	<li>Caso haja fotos para serem exibidas, coloca-las em uma pasta no mesmo diretório em que o arquivo está localizado. O nome da pasta deverá ser igual ao nome dos arquivos.
		O nome das imagens deverá seguir a seguinte lógica: "Cargo do Candidato" (a escrita do cargo deverá ser igual a como está no arquivo de texto) + " "(um espaço em branco)
		+ "Nome do Candidato" (o nome do candidato deverá ser escrito da mesma forma que está no arquivo). Exemplo: Presidente Marina Silva.
	</li>
</ul>
