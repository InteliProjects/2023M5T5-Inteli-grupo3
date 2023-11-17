import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";
import CloseIcon from '@mui/icons-material/Close';
import {IconButton} from "@mui/material";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';

export default function Manual({isOpen, onClose}: { isOpen: boolean, onClose: () => void }) {

    // Estilo do modal do manual
    const style = {
            position: 'absolute' as 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 900,
            height: 700,
            bgcolor: 'background.paper',
            boxShadow: 24,
            p: 4,
            borderRadius: 5,
            display: "flex",
            flexDirection: "column",
            alignItems: "center"
        },
        // Estilo do texto do manual
        manualTextStyle = {
            marginBottom: '20px',
            marginInline: '15px'
        },
        // Estilo da imagem do manual
        manualImageStyle = {
            marginBottom: '20px'
        },
        // Estilo da tabela do manual
        manualTableStyle = {
            marginBottom: '50px'
        }

    return (
        <Modal open={isOpen} aria-labelledby="child-modal-title" aria-describedby="child-modal-description">
            <>
                <Box sx={{...style}}>
                    <IconButton sx={{left: "400px"}} onClick={() => onClose()}>
                        <CloseIcon/>
                    </IconButton>
                    <div style={{overflowY: "auto"}}>
                        <Typography variant="h4" sx={{margin: "20px", marginTop: 0, textAlign: "center"}}>
                            Manual do usuário
                        </Typography>
                        <Typography sx={{...manualTextStyle}}>Bem-vindo à aplicação Umberto. Esta plataforma tem como
                            objetivo otimizar os fluxos de cerveja em ambientes fabris, proporcionando uma visualização
                            mais clara das possíveis rotas da cerveja para uma determinada entrada e saída.</Typography>
                        <Typography sx={{...manualTextStyle}}>Para começar a utilizar a aplicação, basta clicar no botão
                            "+" para adicionar um novo projeto.</Typography>
                        <img src='m001.png' alt='Adicionar projeto'/>
                        <Typography sx={{...manualTextStyle}}>Preencha o formulário com as informações do seu novo
                            projeto. Para baixar o modelo da planilha a ser preenchida, clique no botão "DOWNLOAD DO
                            MODELO DE CSV".</Typography>
                        <img src='m002.png' alt='Download modelo CSV' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Preencha as planilhas seguindo o modelo fornecido e as
                            restrições abaixo:</Typography>
                        <Typography sx={{margin: "10px", marginTop: 0, textAlign: "center"}} variant="h5">Tabela de
                            tanques</Typography>
                        <Typography sx={{textDecoration: "underline", marginTop: 0, textAlign: "center"}}
                                    variant="subtitle1">nodo_tanque.csv</Typography>
                        <TableContainer>
                            <Table sx={{...manualTableStyle, minWidth: 650}} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>name</TableCell>
                                        <TableCell>volume</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableCell>[NOME DO TANQUE]</TableCell>
                                    <TableCell>[CAPACIDADE DO TANQUE]</TableCell>
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <Typography sx={{margin: "10px", marginTop: 0, textAlign: "center"}} variant="h5">Tabela de
                            válvulas</Typography>
                        <Typography sx={{textDecoration: "underline", marginTop: 0, textAlign: "center"}}
                                    variant="subtitle1">nodo_valvula.csv</Typography>
                        <TableContainer>
                            <Table sx={{...manualTableStyle, minWidth: 650}} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>name</TableCell>
                                        <TableCell>type</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableCell>[NOME DA VÁLVULA]</TableCell>
                                    <TableCell>[TIPO DA VÁLVULA]</TableCell>
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <Typography sx={{margin: "10px", marginTop: 0, textAlign: "center"}} variant="h5">Tabela de
                            In&Outs</Typography>
                        <Typography sx={{textDecoration: "underline", marginTop: 0, textAlign: "center"}}
                                    variant="subtitle1">nodo_inout.csv</Typography>
                        <Typography sx={{margin: "20px", marginTop: 0, textAlign: "center"}} variant="h5"></Typography>
                        <TableContainer>
                            <Table sx={{...manualTableStyle, minWidth: 650}} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>name</TableCell>
                                        <TableCell>type</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableCell>[NOME DO IN&OUT]</TableCell>
                                    <TableCell>[TIPO DO IN&OUT]</TableCell>
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <Typography sx={{margin: "10px", marginTop: 0, textAlign: "center"}} variant="h5">Tabela de
                            Tubulações</Typography>
                        <Typography sx={{textDecoration: "underline", marginTop: 0, textAlign: "center"}}
                                    variant="subtitle1">relacionamentos.csv</Typography>
                        <TableContainer>
                            <Table sx={{...manualTableStyle, minWidth: 650}} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>type_source</TableCell>
                                        <TableCell>source</TableCell>
                                        <TableCell>type_target</TableCell>
                                        <TableCell>target</TableCell>
                                        <TableCell>type</TableCell>
                                        <TableCell>length</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow>
                                        <TableCell>TANQUE</TableCell>
                                        <TableCell>[NOME DA ORIGEM]</TableCell>
                                        <TableCell>VALVULA</TableCell>
                                        <TableCell>[NOME DO DESTINO]</TableCell>
                                        <TableCell>[TIPO DA TUBULAÇÃO]</TableCell>
                                        <TableCell>[COMPRIMENTO DA TUBULAÇÃO]</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell>VALVULA</TableCell>
                                        <TableCell>[NOME DA ORIGEM]</TableCell>
                                        <TableCell>TANQUE</TableCell>
                                        <TableCell>[NOME DO DESTINO]</TableCell>
                                        <TableCell>[TIPO DA TUBULAÇÃO]</TableCell>
                                        <TableCell>[COMPRIMENTO DA TUBULAÇÃO]</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell>INOUT</TableCell>
                                        <TableCell>[NOME DA ORIGEM]</TableCell>
                                        <TableCell>VALVULA</TableCell>
                                        <TableCell>[NOME DO DESTINO]</TableCell>
                                        <TableCell>[TIPO DA TUBULAÇÃO]</TableCell>
                                        <TableCell>[COMPRIMENTO DA TUBULAÇÃO]</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <Typography sx={{...manualTextStyle, fontWeight: "bold"}}>OBS.: As colunas 'type_source' e
                            'type_target' devem seguir uma das opções na tabela de exemplo acima ('TANQUE',
                            'VALVULA'...).</Typography>
                        <Typography sx={{...manualTextStyle, fontWeight: "bold"}}>OBS.: O CSV deve utilizar ';' como
                            delimitador padrão.</Typography>
                        <Typography sx={{...manualTextStyle}}>Após preencher as planilhas, faça o upload dos arquivos
                            usando os botões abaixo (os botões ficarão verdes se os arquivos estiverem prontos para
                            serem enviados):</Typography>
                        <img src='m003.png' alt='Upload tabelas' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Clique em "ADICIONAR" para criar o novo
                            projeto.</Typography>
                        <img src='m004.png' alt='Adicionar tabelas' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Agora, o projeto estará disponível na página de projetos.
                            Se houver muitos projetos, é possível procurar por um projeto específico usando a barra de
                            pesquisa. Se o mesmo não existir, a plataforma será redirecionada para a página de criação
                            de projetos.</Typography>
                        <img src='m005.png' alt='Pesquisar por projetos' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Para editar um projeto, basta passar o mouse sobre o card
                            do projeto escolhido.</Typography>
                        <img src='m006.png' alt='Ações no projeto' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Utilizando os botões da imagem acima, é possível editar o
                            nome do projeto ou excluí-lo.</Typography>
                        <Typography sx={{...manualTextStyle}}>Para acessar um projeto, basta clicar no card do projeto
                            escolhido.</Typography>
                        <img src='m007.png' alt='Selecionar um projeto' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Abaixo, é possível visualizar o projeto selecionado.
                            Inicialmente, toda a planta da fábrica é representada em forma de um grafo.</Typography>
                        <img src='m008.png' alt='Página inicial do projeto' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Para escolher uma origem e um destino, basta selecionar
                            dentre as opções nos 2 primeiros campos, onde o primeiro deles será a origem e, o segundo, o
                            destino. Os outros 2 campos inferiores são limitações de caminho, selecionando um tipo
                            específico de relacionamento (ou seja, o campo 'type' do
                            'relacionamentos.csv').</Typography>
                        <Typography sx={{...manualTextStyle}}>Após a filtragem, o botão "GET" exibirá o melhor caminho
                            na tela.</Typography>
                        <img src='m009.png' alt='Visualização de um caminho' style={{...manualImageStyle}}/>
                        <Typography sx={{...manualTextStyle}}>Os botões R1, R2, R3... mostram outras rotas possíveis com
                            a mesma origem e destino.</Typography>
                        <Typography sx={{...manualTextStyle}}>O botão "DOWNLOAD" no canto superior direto pode ser
                            utilizado para baixar as rotas em formato de planilha personalizada para leiura de
                            máquina.</Typography>
                    </div>
                </Box>
            </>
        </Modal>
    );
}